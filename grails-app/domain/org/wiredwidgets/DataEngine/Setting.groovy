package org.wiredwidgets.DataEngine

class Setting
{
	boolean active = true
	String key
	String value
	static constraints = {
		key( unique: true )
	}

	/** returns the network.proxy value
	 *  iff key and value are defined and active is true
	 *  else null
	 */
	static String getNetworkProxy()
	{
		def networkproxy = Setting.findByKey( "network.proxy" )
		if ( networkproxy && networkproxy.active && networkproxy.value )
		{
			return networkproxy.value
		}
		else
		{
			return null
		}
	}
}
