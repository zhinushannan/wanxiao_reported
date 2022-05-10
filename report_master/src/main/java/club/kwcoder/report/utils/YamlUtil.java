package club.kwcoder.report.utils;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author yichuan@iscas.ac.cn
 * 修改YmL文件的工具类
 * @version 1.0
 * @date 2020/11/14 17:01
 */
public class YamlUtil {
    private final static DumperOptions OPTIONS = new DumperOptions();

    private static File file;

    private static InputStream ymlInputSteam;

    private static Object CONFIG_MAP;

    private static Yaml yaml;

    static {
        //将默认读取的方式设置为块状读取
        OPTIONS.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
    }

    /**
     * 使用其他方法之前必须调用一次 设置yml的输出文件,当没有设置输入流时可以不设置输入流,默认以此文件读入
     *
     * @param file 输出的文件
     */
    public static void setYmlFile(File file) throws FileNotFoundException {
        YamlUtil.file = file;
        if (ymlInputSteam == null) {
            setYmlInputSteam(new FileInputStream(file));
        }
    }


    /**
     * 使用其他方法之前必须调用一次 设置yml的输入流
     *
     * @param inputSteam 输入流
     */
    public static void setYmlInputSteam(InputStream inputSteam) {
        ymlInputSteam = inputSteam;
        yaml = new Yaml(OPTIONS);
        CONFIG_MAP = yaml.load(ymlInputSteam);
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 查询到的值
     */
    @SuppressWarnings("unchecked")
    public static Object getByKey(String key) {
        if (ymlInputSteam == null) {
            return null;
        }
        String[] keys = key.split("\\.");
        Object configMap = CONFIG_MAP;
        for (String s : keys) {
            if (configMap instanceof Map) {
                configMap = ((Map<String, Object>) configMap).get(s);
            } else {
                break;
            }
        }
        return configMap == null ? "" : configMap;
    }

    public static void saveOrUpdateByKey(String key, Object value) throws IOException {
        KeyAndMap keyAndMap = new KeyAndMap(key).invoke();
        key = keyAndMap.getKey();
        Map<String, Object> map = keyAndMap.getMap();
        map.put(key, value);
        //将数据重新写回文件
        yaml.dump(CONFIG_MAP, new FileWriter(file));
    }

    public static void removeByKey(String key) throws Exception {
        KeyAndMap keyAndMap = new KeyAndMap(key).invoke();
        key = keyAndMap.getKey();
        Map<String, Object> map = keyAndMap.getMap();
        Map<String, Object> fatherMap = keyAndMap.getFatherMap();
        map.remove(key);
        if (map.size() == 0) {
            Set<Map.Entry<String, Object>> entries = fatherMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                if (entry.getValue() == map) {
                    fatherMap.remove(entry.getKey());
                }
            }
        }
        yaml.dump(CONFIG_MAP, new FileWriter(file));
    }

    private static class KeyAndMap {
        private String key;
        private Map<String, Object> map;
        private Map<String, Object> fatherMap;

        public KeyAndMap(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public Map<String, Object> getFatherMap() {
            return fatherMap;
        }

        public KeyAndMap invoke() {
            if (file == null) {
                System.err.println("请设置文件路径");
            }
            if (null == CONFIG_MAP) {
                CONFIG_MAP = new LinkedHashMap<>();
            }
            String[] keys = key.split("\\.");
            key = keys[keys.length - 1];
            map = (Map<String, Object>) CONFIG_MAP;
            for (int i = 0; i < keys.length - 1; i++) {
                String s = keys[i];
                if (map.get(s) == null || !(map.get(s) instanceof Map)) {
                    map.put(s, new HashMap<>(4));
                }
                fatherMap = map;
                map = (Map<String, Object>) map.get(s);
            }
            return this;
        }
    }
}

