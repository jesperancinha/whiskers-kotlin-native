import kotlin.test.Test
import kotlin.test.assertEquals

class DtoKtTest {

    @Test
    fun `should encode text`() {
        val catSayings = listOf(CatSaying(id = 1, "Yummi Yummi Yummi -!@#$%^&*()")).toEncodedSayings()

        assertEquals(1, catSayings.size)
        assertEquals("5020121208 5020121208 5020121208 0-0!0@0#0\$0%0^0&0*0(0)", catSayings[0].saying)
    }
}