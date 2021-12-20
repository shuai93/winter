package tk.shuai93.winter.common.conf;

import com.power.common.util.DateTimeUtil;
import com.power.doc.builder.ApiDocBuilder;
import com.power.doc.model.ApiConfig;

import java.io.File;

/**
 * <p>
 * ApiConfig
 * </p>
 *
 * @author shuai.yang1
 * @since 2021-12-19
 */
public class BuildApi {
    public static void main(String[] args) {
            ApiConfig config = new ApiConfig();
            config.setServerUrl("http://localhost:80");

            //设置为严格模式，Smart-doc将降至要求每个Controller暴露的接口写上标准文档注释
            //config.setStrict(true);
            config.setStrict(false);
            //当把AllInOne设置为true时，Smart-doc将会把所有接口生成到一个Markdown、HHTML或者AsciiDoc中
            config.setAllInOne(true);

            //Set the api document output path.
            config.setOutPath("/Users/shuai.yang1/Desktop/api");
            String url="/Users/shuai.yang1/Desktop/api";
            File file=new File(url);
            if (!file.exists()){
                file.mkdir();
            }
            // 设置接口包扫描路径过滤，如果不配置则Smart-doc默认扫描所有的接口类
            // 配置多个报名有英文逗号隔开
            // config.setPackageFilters("com.power.doc.controller");

            //since 1.7.5
            //如果该选项的值为false,则smart-doc生成allInOne.md文件的名称会自动添加版本号
            config.setCoverOld(true);
            //since 1.7.5
            //设置项目名(非必须)，如果不设置会导致在使用一些自动添加标题序号的工具显示的序号不正常
            config.setProjectName("smart-doc测试接口文档");
            config.setPackageFilters("com.nio.dd.sdi.muses.music.controller.admin");

           //      设置公共请求头.如果不需要请求头，则无需设置
           // config.setRequestHeaders(
           //         ApiReqHeader.header().setName("access_token").setType("string")
           //                 .setDesc("Basic auth credentials").setRequired(true).setSince("v1.1.0"),
           //         ApiReqHeader.header().setName("user_uuid").setType("string").setDesc("User Uuid key")
           // );

            long start = System.currentTimeMillis();
            //生成Markdown文件
            // ApiDocBuilder.buildApiDoc(config);
            // HtmlApiDocBuilder.buildApiDoc(config);
            // PostmanJsonBuilder.buildPostmanCollection(config);
            ApiDocBuilder.buildApiDoc(config);

            long end = System.currentTimeMillis();
            DateTimeUtil.printRunTime(end, start);
        }
    }
