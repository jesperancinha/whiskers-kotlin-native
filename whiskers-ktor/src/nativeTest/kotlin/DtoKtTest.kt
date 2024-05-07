import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertEquals

class DtoKtTest {

    @Test
    fun `should encode text`() {
        runBlocking {
            val catSayings = listOf(CatSaying(id = 1, "Yummi Yummi Yummi -!@#$%^&*()"))
                .toEncodedSayings()
                .toList()

            assertEquals(1, catSayings.size)
            assertEquals("5020121208 5020121208 5020121208 0-0!0@0#0\$0%0^0&0*0(0)", catSayings[0].saying)
        }
    }
}