# 环境搭建

esclipse设置maven   https://blog.csdn.net/wcc27857285/article/details/81812304

注意新建的时候如果空 可以下拉选择到想选的

maven 的 setting.xml设置阿里云  https://blog.csdn.net/cocplay/article/details/81782207

javafx的开源bootstarp的库 https://baijiahao.baidu.com/s?id=1677555457338422623&wfr=spider&for=pc

问题： esclipse的javafx不提示  https://www.cnblogs.com/Lionel002/archive/2016/11/05/6032260.html



demo的例子

```java
import org.kordamp.bootstrapfx.scene.layout.Panel;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Panel panel = new Panel("BootstrapFx Demo");
		panel.getStyleClass().add("panel-primary");
		BorderPane content = new BorderPane();
		content.setPadding(new Insets(20));
		Button button = new Button("你好，我是一个BootstrapFx按钮");
		button.getStyleClass().setAll("btn","btn-danger");
		content.setCenter(button);
		panel.setBody(content);
		
		Scene scene = new Scene(panel);
		scene.getStylesheets().add("org/kordamp/bootstrapfx/bootstrapfx.css");
		
		primaryStage.setTitle("测试窗体");
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
```



esclipse关于新加resource

https://www.cnblogs.com/testway/p/5296264.html



javafx学习 ： https://github.com/AlmasB/WeatherApp/blob/master/src/main/resources/com/almasb/weather/ui.fxml

ps

看vip视频 https://github.com/kedpter/vip-player-plugin