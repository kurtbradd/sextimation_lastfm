import org.apache.spark.{SparkConf, SparkContext}, SparkContext._
import org.apache.spark.mllib.linalg.{Vector, Vectors, Matrix}
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import scala.collection.mutable
import scala.io.Source._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.DefaultFormats

object SextimationParser {

  def main(args: Array[String]) {

    val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SextimationParser")
    .setSparkHome("SPARK_HOME")
    .setJars(List("target/scala-2.10/sextimation-parser_2.10-1.0.jar"))
    val sc = new SparkContext(conf)

    implicit val formats = DefaultFormats
    val artists = mutable.Set[String]()
    val artist_name_map = mutable.Map[String, Int]()

    val json_file = fromFile("userartistsexratio_bak.json")
    var data = json_file.getLines.toArray;
    val json = parse(data(0))

    val users_data = json.extract[Map[String, Map[String, JValue]]];
    val user_ids = users_data.keys.toList;

    val user_songs = user_ids.map(user_id => {
      users_data(user_id)("artists").values.asInstanceOf[Map[String, Int]]
    })

    val user_demographic = user_ids.map(user_id => {
      users_data(user_id)("demographics").values.asInstanceOf[Map[String, Any]]
    })
    
    user_songs.foreach(song_map => artists ++= song_map.keys.toSet)
    artists.toList.zipWithIndex.foreach( artist_name_map += _)
  }
}