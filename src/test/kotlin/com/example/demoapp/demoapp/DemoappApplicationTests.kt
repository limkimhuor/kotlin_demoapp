package com.example.demoapp.demoapp

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment =
SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoappApplicationTests {

	@Autowired
	lateinit var testRestTemplate: TestRestTemplate

	@Test
    fun testHelloController() {
        val result = testRestTemplate.getForEntity("/hello/string", String::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "Hello string!")
    }

    @Test
    fun testHelloService() {
        val result = testRestTemplate.getForEntity("/hello/service", String::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, "Hello service!")
    }

    @Test
	fun testHelloData() {
        val result = testRestTemplate.getForEntity("/hello/data", Hello::class.java)
        assertNotNull(result)
        assertEquals(result.statusCode, HttpStatus.OK)
        assertEquals(result.body, Hello("Hello data!"))
	}

}


@RunWith(MockitoJUnitRunner::class) class HelloControllerUnitTest {
    @InjectMocks
    lateinit var helloController: HelloController

    @Mock
    lateinit var helloService: HelloService

    @Test
    fun testHelloController() {
        val result = helloController.helloString()
        assertNotNull(result)
        assertEquals("Hello string!", result)
    }

    @Test
    fun testHelloService() {
        val result = helloService.getHello()
        assertNotNull(result)
        assertEquals("Hello service!", result)
    }

    @Test
    fun testHelloData() {
        val result = helloController.helloData()
        assertNotNull(result)
        assertEquals("Hello data!", result)
    }
}
