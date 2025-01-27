import org.springframework.context.annotation.Bean;

public class BuyMaConfig {

	@Bean
	public BuyMaRequestInterceptor buyMaRequestInterceptor() {
		return new BuyMaRequestInterceptor();
	}
}
