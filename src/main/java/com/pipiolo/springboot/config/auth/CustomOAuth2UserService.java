package com.pipiolo.springboot.config.auth;

import com.pipiolo.springboot.config.auth.dto.OAuthAttributes;
import com.pipiolo.springboot.config.auth.dto.SessionUser;
import com.pipiolo.springboot.domain.user.User;
import com.pipiolo.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Deprecated
@RequiredArgsConstructor
//@Service
public class CustomOAuth2UserService
//        implements OAuth2UserService<OAuth2UserRequest, OAuth2User>
{

    private final UserRepository userRepository;
    private final HttpSession    httpSession;

//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
//        OAuth2User oAuth2User                                     = delegate.loadUser(userRequest);
//
//        String reigstationId         = userRequest.getClientRegistration().getRegistrationId();
//        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
//
//        OAuthAttributes attributes = OAuthAttributes.of(reigstationId, userNameAttributeName, oAuth2User.getAttributes());
//
//        User user = saveOrUpdate(attributes);
//
//        httpSession.setAttribute("user", new SessionUser(user));
//
//        return new DefaultOAuth2User(
//                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
//                attributes.getAttributes(),
//                attributes.getNameAttributeKey());
//    }

//    private User saveOrUpdate(OAuthAttributes attributes) {
//        User user = userRepository.findByEmail(attributes.getEmail())
//                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
//                .orElse(attributes.toEntity());
//
//        return userRepository.save(user);
//    }
}
