import kotlin.test.Test
import kotlin.test.assertEquals

class DaoKtTest {

    @Test
    fun shouldEscapeSentence() {
        val text =
            "Lucy's suitcase was checked entering Hong Kong. Red Portal Enterprises mentioned nothing about a business card"
        val result = text.escapePostgreSQL()
        assertEquals(
            "Lucy''s suitcase was checked entering Hong Kong. Red Portal Enterprises mentioned nothing about a business card",
            result
        )
    }
}