package yo.benqcz.xmldb.dao.impl;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import yo.benqcz.xmldb.dao.XmlDao;
import yo.benqcz.xmldb.domain.Student;

public abstract class AbstractXmlDao implements XmlDao {
	
	protected Map<String, Student> oldStudents = Collections.synchronizedMap(new HashMap<String, Student>());
	protected Map<String, Student> newStudents = Collections.synchronizedMap(new HashMap<String, Student>());

}
