import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MySqlGenerator {
	public static void main(String[] args) {
		List<String> warnings = new ArrayList<String>();
		final boolean overwrite = true;
		File configFile = new File("src/test/resources/generatorConfig.xml");
		 System.out.println("config file: " + configFile.getAbsoluteFile());
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration configuration;
		try {
			configuration = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator mybatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
			mybatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
