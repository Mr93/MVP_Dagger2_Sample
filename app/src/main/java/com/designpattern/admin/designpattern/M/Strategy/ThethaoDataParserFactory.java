package com.designpattern.admin.designpattern.M.Strategy;

import android.app.Activity;

/**
 * Created by DucDt on 9/14/2016.
 */

public class ThethaoDataParserFactory implements DataParserFactory {

	@Override
	public  DataParser createDataParser(String domain, Activity context) {
		if (domain.contains(Thethao24hParser.DOMAIN)) {
			return Thethao24hParser.getInstance(context);
		} else if (domain.contains(Thethao247Parser.DOMAIN)) {
			return Thethao247Parser.getInstance(context);
		}
		return NullDomainParser.getInstance();
	}


}
