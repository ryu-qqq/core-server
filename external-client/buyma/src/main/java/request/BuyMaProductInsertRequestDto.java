package request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuyMaProductInsertRequestDto {
        @JsonProperty("reference_number")
        private String referenceNumber;

        @JsonProperty("control")
        private String control;

        @JsonProperty("name")
        private String name;

        @JsonProperty("comments")
        private String comments;

        @JsonProperty("brand_id")
        private int brandId;

        @JsonProperty("brand_name")
        private String brandName;

        @JsonProperty("category_id")
        private int categoryId;

        @JsonProperty("price")
        private int price;

        @JsonProperty("reference_price")
        private int referencePrice;

        @JsonProperty("available_until")
        private String availableUntil;

        @JsonProperty("buying_area_id")
        private  int buyingAreaId;

        @JsonProperty("shipping_area_id")
        private int shippingAreaId;

        @JsonProperty("images")
        private List<BuyMaImageInsertRequestDto> images;

        @JsonProperty("shipping_methods")
        private List<BuyMaShippingMethodDto> shippingMethods;

        @JsonProperty("variants")
        private List<BuyMaVariantInsertRequestDto> variants;

        @JsonProperty("options")
        private List<BuyMaOptionInsertRequestDto> options;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @JsonProperty("id")
        private  Integer id;

        @JsonProperty("duty")
        private String duty;

        @JsonProperty("colorsize_comments")
        private String colorSizeComment;


        public BuyMaProductInsertRequestDto(String styleCode, long productGroupId, String name, String comments, String brandId, String brandName, String categoryId, int price, int referencePrice, List<BuyMaImageInsertRequestDto> images, List<BuyMaVariantInsertRequestDto> variants, List<BuyMaOptionInsertRequestDto> options, String id, String colorSizeComment) {
                this.referenceNumber = DEFAULT_REFERENCE_NUMBER(styleCode, productGroupId);
                this.control = "publish";
                this.name = name;
                this.comments = comments;
                this.brandId = Integer.parseInt(brandId);
                this.brandName = brandName;
                this.categoryId = Integer.parseInt(categoryId);
                this.price = price;
                this.referencePrice = referencePrice;
                this.availableUntil = LocalDateTime.now().plusDays(90).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                this.buyingAreaId = 2002003001;
                this.shippingAreaId = 2002003001;
                this.images = images;
                this.shippingMethods = DEFAULT_SHIPPING_METHODS();
                this.variants = variants;
                this.options = options;
                this.id = id != null ? Integer.parseInt(id) : null;
                this.duty = "included";
                this.colorSizeComment = colorSizeComment;
        }


        private List<BuyMaShippingMethodDto> DEFAULT_SHIPPING_METHODS(){
                return List.of(
                        new BuyMaShippingMethodDto(984481),
                        new BuyMaShippingMethodDto(984491)
                );
        }

        private String DEFAULT_REFERENCE_NUMBER(String styleCode, long productGroupId) {
                StringBuilder sb = new StringBuilder();
                sb.append(productGroupId);

                if (styleCode != null && !styleCode.isEmpty()) {
                        sb.append("_");
                        sb.append(styleCode);
                }
                return sb.toString();
        }





}
