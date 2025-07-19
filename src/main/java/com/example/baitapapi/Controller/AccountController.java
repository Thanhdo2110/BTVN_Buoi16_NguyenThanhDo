package com.example.baitapapi.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

    @RestController
    public class AccountController {

      @GetMapping("hello")
      public ResponseEntity<?> hello (){
          return ResponseEntity.ok("helo spring boot");
      }
      @GetMapping("/greet")
       public ResponseEntity<?> greet (@RequestParam(value = "name" , required = false)String name){
          if (name == null || name.isEmpty()){
              return ResponseEntity.ok("Chao ban?");
          }
          return ResponseEntity.ok("Chao "+name);
      }
        @GetMapping("/sum")
        public ResponseEntity<String> sum(@RequestParam(value = "a", required = false) String a,
                                          @RequestParam(value = "b", required = false) String b) {
            if (a == null && b == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Không truyền dữ liệu cả 2");
            }

            if (a == null || b == null) {
                return ResponseEntity.ok("Tổng: 0");
            }

            try {
                int numA = Integer.parseInt(a);
                int numB = Integer.parseInt(b);
                int result = numA + numB;
                return ResponseEntity.ok("Tổng: " + result);
            } catch (NumberFormatException e) {
                return ResponseEntity.ok("Tổng: 0");
            }
        }

        @GetMapping("/users/{id}")
        public ResponseEntity<?> getUserById (@PathVariable("id")String id){
          return ResponseEntity.ok("User co id: "+id);
        }

      @GetMapping("/users/{userId}/posts/{postId}")
      public ResponseEntity<?> getUserPost (@PathVariable("userId")String userId,
                                             @PathVariable("postId")String postId){
          return ResponseEntity.ok("Bai dang cua "+userId+" co id "+postId);
      }

      @GetMapping("square/{number}")
      public ResponseEntity<?> number (@PathVariable("number")String number){
          try{
              int num = Integer.parseInt(number);
              int result = num * num ;
              return ResponseEntity.ok("He so la "+result);
          }catch (NumberFormatException e){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nhap phai la so?");
          }
      }

        @GetMapping("/rectangle/{width}/{height}")
        public ResponseEntity<String> rectangle(@PathVariable("width") String width,
                                                @PathVariable("height") String height) {
            try {
                int w = Integer.parseInt(width);
                int h = Integer.parseInt(height);
                int area = w * h;
                return ResponseEntity.ok("Diện tích HCN: " + area);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Tham số truyền 2 cạnh HCN phải là số");
            }
        }

        @GetMapping("/divide")
        public ResponseEntity<String> divide(@RequestParam("a") String a,
                                             @RequestParam("b") String b) {
            try {
                int numA = Integer.parseInt(a);
                int numB = Integer.parseInt(b);

                if (numB == 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Không thể chia cho 0");
                }

                double result = (double) numA / numB;
                return ResponseEntity.ok("Kết quả: " + result);
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Tham số phải là số");
            }
        }

      @GetMapping("/check")
      public ResponseEntity<?> check (@RequestParam("number")String number){
          try {
              int num = Integer.parseInt(number);
              if (num % 2 == 0){
                  return ResponseEntity.ok("La so chan!");
              }else {
                  return ResponseEntity.ok("La so le!");
              }
          }catch (NumberFormatException e){
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nhap so di . Ai muon nhap ki tu?");
          }
      }

        @PutMapping("/products/{id}")
        public ResponseEntity<String> updateProduct(@PathVariable("id") String id,
                                                    @RequestBody Map<String, Object> productData) {

            return ResponseEntity.ok("Cập nhật thành công");
        }

        @DeleteMapping("/products/{id}")
        public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
            return ResponseEntity.ok("Đã xoá sản phẩm có ID = " + id);
        }

        @GetMapping("/bmi")
        public ResponseEntity<String> calculateBMI(@RequestParam("weight") String weight,
                                                   @RequestParam("height") String height) {
            try {
                double w = Double.parseDouble(weight);
                double h = Double.parseDouble(height);

                if (w <= 0 || h <= 0) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("Cân nặng và chiều cao phải lớn hơn 0");
                }

                double bmi = w / (h * h);
                String classification = classifyBMI(bmi);

                return ResponseEntity.ok(String.format("BMI: %.1f - %s", bmi, classification));
            } catch (NumberFormatException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Cân nặng và chiều cao phải là số");
            }
        }
        private String classifyBMI(double bmi) {
            if (bmi < 18.5) {
                return "Thiếu cân";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                return "Bình thường";
            } else if (bmi >= 25 && bmi <= 29.9) {
                return "Thừa cân";
            } else {
                return "Béo phì";
            }
        }
    }

