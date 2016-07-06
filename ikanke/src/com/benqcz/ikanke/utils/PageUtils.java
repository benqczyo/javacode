/**
 * 
 */
package com.benqcz.ikanke.utils;

import com.benqcz.ikanke.domain.impl.Page;

public abstract class PageUtils {
	
	public static Page getInstance(int totalRecords, int recordOfSinglePage,
			int buttonsOfSinglePage, int currentPageId) {
		return new Page(totalRecords, recordOfSinglePage, buttonsOfSinglePage, currentPageId);
	}
	
}
