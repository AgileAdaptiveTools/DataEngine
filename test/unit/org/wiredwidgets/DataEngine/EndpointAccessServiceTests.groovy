package org.wiredwidgets.DataEngine

import grails.test.mixin.*

import org.wiredwidgets.DataEngine.EndpointAccessService
import grails.plugins.rest.client.RestBuilder

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(EndpointAccessService)
class EndpointAccessServiceTests
{
	void testGetRestBuilder( )
	{
		def builder = EndpointAccessService.getRestBuilder()
		assert builder != null
		assert builder instanceof RestBuilder

		builder = EndpointAccessService.getRestBuilder( "localhost:8888" )
		assert builder != null
		assert builder instanceof RestBuilder

		builder = EndpointAccessService.getRestBuilder( "localhost", "8888" )
		assert builder != null
		assert builder instanceof RestBuilder
	}
}
