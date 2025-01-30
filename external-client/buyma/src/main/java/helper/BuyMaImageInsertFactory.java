package helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ryuqq.core.domain.product.core.ItemImage;

import request.BuyMaImageInsertRequestDto;

@Component
public class BuyMaImageInsertFactory {

	public List<BuyMaImageInsertRequestDto> generateImageContext(List<? extends ItemImage> itemImage){
		List<BuyMaImageInsertRequestDto> buyMaImageInsertRequests = new ArrayList<>();
		int imageSortCounter = 2;

		for (ItemImage img : itemImage) {
			if (img.getProductImageType().isMain()) {
				buyMaImageInsertRequests.add(new BuyMaImageInsertRequestDto(
					img.getImageUrl(),
					1
				));
			} else {
				buyMaImageInsertRequests.add(new BuyMaImageInsertRequestDto(
					img.getImageUrl(),
					imageSortCounter
				));
				imageSortCounter++;
			}
		}

		return buyMaImageInsertRequests;
	}


}
