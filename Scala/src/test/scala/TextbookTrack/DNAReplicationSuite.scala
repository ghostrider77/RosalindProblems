package TextbookTrack

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

class DNAReplicationSuite extends AnyFreeSpec with Matchers {

  "k-mer problems" - {

    "ba1a" - {
      import BA1A.countPattern

      "should return the number of times pattern appears in text" - {
        "test case 1" in {
          val text: String = "GCGCG"
          val pattern: String = "GCG"
          countPattern(text, pattern) shouldEqual 2
        }

        "test case 2" in {
          val text: String = "ACAACTATGCATACTATCGGGAACTATCCT"
          val pattern: String = "ACTAT"
          countPattern(text, pattern) shouldEqual 3
        }

        "test case 3" in {
          val text: String = "CGATATATCCATAG"
          val pattern: String = "ATA"
          countPattern(text, pattern) shouldEqual 3
        }
      }
    }

    "ba1b" - {
      import BA1B.mostFrequentKMer

      "should collect the most frequent k-mers from text" - {
        "test case 1" in {
          val text: String = "ACGTTGCATGTCGCATGATGCATGAGAGCT"
          val k: Int = 4
          mostFrequentKMer(text, k) should contain theSameElementsAs List("CATG", "GCAT")
        }

        "test case 2" in {
          val text: String = "ACAACTATGCATCACTATCGGGAACTATCCT"
          val k: Int = 5
          mostFrequentKMer(text, k) should contain theSameElementsAs List("ACTAT")
        }
      }
    }
  }
}