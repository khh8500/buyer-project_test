package com.example.buyer.cart;

import com.example.buyer.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CartController {

    private final CartService cartService;
    private final HttpSession session;

    // 장바구니 담기
    @PostMapping("/cart")
    public String cart(CartRequest.SaveDTO reqDTO){

        User sessionUser = (User) session.getAttribute("sessionUser");
        System.out.println("???reqDTO = " + reqDTO);
        cartService.saveCart(reqDTO, sessionUser);

        return "redirect:/cart-form";
    }

    @GetMapping("/cart-form")
    public String cartForm(HttpServletRequest request){

        List<CartResponse.SaveDTO> cartList = cartService.findAll();
        System.out.println("cartList = " + cartList);
        request.setAttribute("cartList", cartList);

        return "cart/cart-form";
    }
}
