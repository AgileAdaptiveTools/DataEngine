package org.wiredwidgets.DataEngine


class SettingController
{
	def scaffold = Setting

	def index()
	{
		println ("SettingController.index():  ${params.sort{it.key}}")

		// get existing values for form from database
		def networkProxyObject = Setting.findByKey( "network.proxy" )
		def useProxy = networkProxyObject?.active
		def proxyhostport = networkProxyObject?.value

		[useProxy:useProxy,
		 proxyhostport:proxyhostport,
		]
	}

	def saveSettings()
	{
		println ("SettingController.saveSettings():  ${params.sort{it.key}}")

		def networkProxyObject = Setting.findByKey( "network.proxy" )
		networkProxyObject.active = params.useProxy == "true" ? true : false;
		networkProxyObject.value = params.proxyhostport

		networkProxyObject.save(true)

		flash.message = "Your changes were saved."
		forward(action:"index")
	}
}