package org.wiredwidgets.DataEngine



import grails.test.mixin.*
import org.junit.*
import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(IdlController)
@Mock(Idl)
class IdlControllerTests
{
    static String json1 = '''{
                "description":"descriptions",
                "dslv": "dslv",
                "poc": "poc",
                "source": "source",
                "source_uri": "sourceuri",
                "title": "title"
              } '''.stripIndent()

    static String jsonMissingRequiredParam1 = json1.replaceAll( '''"source": "source",''', '' )

    static String jsonMalformed1 = '''{
                "description":"descriptions"
                "dslv": "dslv",
                "poc": "poc",
                "source": "source",
                "source_uri": "sourceuri",
                "title": "title"
              } '''.stripIndent()

    // ----- GET -----

    void test_RestList_emptyTable()
    {
        controller.get()
//        println response.text
        // string compare, first character is " "
        assert response.text[1] == "["
        assert response.text[response.text.size()-1] == "]"
        assert response.status == 200
        def obj = JSON.parse( response.text )
        assert obj.size() == 0
    }

    // ----- POST -----

    void test_RestPost()
    {
        // mock HTTP post
        controller.request.contentType = 'application/json'
        controller.request.content = json1.getBytes()
        controller.post()
        assert response.status == 201
        def obj = JSON.parse( response.text )
        assert obj.remark == "Created"
    }

    void test_RestPost_err400_syntaxError()
    {
        // mock HTTP post
        controller.request.contentType = 'application/json'
        controller.request.content = jsonMalformed1.getBytes()
        controller.post()
        assert response.status == 400
        def obj = JSON.parse( response.text )
        assert obj.remark == "Syntax error:  unable to parse input JSON string"
    }

    void test_RestPost_err400_missingRequiredParams()
    {
        // mock HTTP post
        controller.request.contentType = 'application/json'
        println jsonMissingRequiredParam1
        controller.request.content = jsonMissingRequiredParam1.getBytes()
        controller.post()
        assert response.status == 400
        def obj = JSON.parse( response.text )
        assert obj.remark == "Required data missing, or syntax/format error"
    }
}
