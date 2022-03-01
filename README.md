# trueshop

간단한 쇼핑몰 프로젝트입니다.

로그인, 상품조회, 장바구니, 구매이력, 상품정보 및 댓글, 상품주문의 기능이 있습니다.

댓글의 삭제기능은 같은 계정의 댓글만 / 혹은 같은 익명의 댓글만 삭제 가능합니다.

application.properties에 있는

itemImgLocation=C:/Users/hanky/Desktop/Shop/Item_images
uploadPath=file:///C:/Users/hanky/Desktop/Shop/

경로를 수정하신 다음 spring.datasource 또한 수정해주시면 정상적으로 정상적으로 테스트 해보실 수 있습니다.

뷰 템플릿으로는 Thymeleaf을 사용했습니다.


Spring Boot / Thymeleaf / JPA / MySQL
