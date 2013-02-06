package org.wiredwidgets.DataEngine

import grails.plugins.rest.client.RestBuilder

/**
 * EndpointAccessService works with the Setting domain class to provide a simple fascade to access a given URL
 * endpoint.  Specifically it relies on the Setting "network.proxy" to contain an active value (e.g., "proxyserver:8080" )
 * that it can use for a proxy server hostname and port.
 */
class EndpointAccessService
{
	/** returns a RestBuilder with no proxy */
    static RestBuilder getRestBuilder()
	{
		return new RestBuilder()
    }

	/** returns a RestBuilder using specified proxy */
	static RestBuilder getRestBuilder( String proxyhostnameAndPort )
	{
		if ( proxyhostnameAndPort )
		{
			def separatorIndex = proxyhostnameAndPort.indexOf( ':' )
			def proxyhostname = proxyhostnameAndPort.substring( 0, separatorIndex )
			def proxyhostport = proxyhostnameAndPort.substring( separatorIndex+1 )

			return getRestBuilder( proxyhostname, proxyhostport )
		}
		else	// if no proxy is set, then try without a proxy
				// @Todo hk:  not sure if we should throw an exception instead
		{
			return getRestBuilder()
		}
	}

	/** returns a RestBuilder using specified proxy */
	static RestBuilder getRestBuilder( String proxyhostname, String port )
	{
		return new RestBuilder(
				connectTimeout: 1000,
				readTimeout: 20000,
				proxy: [ "${proxyhostname}":"${port}" ] )
	}

}
