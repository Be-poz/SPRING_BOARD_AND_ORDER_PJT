package ksy.geshi.service;

import ksy.geshi.domain.MemberEntity;
import ksy.geshi.form.MemberForm;
import ksy.geshi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public void userRegister(MemberEntity member) {
        userRepository.save(member);
    }

    public boolean checkUserIdExist(String user_id) {
        Optional<MemberEntity> findMember = userRepository.findById(user_id);
        if(findMember.isPresent()==true) return false;
        else return true;
    }

    public MemberForm findUser(String userId) {
        MemberEntity memberEntity = userRepository.findById(userId).get();
        MemberForm memberForm=new MemberForm();
        memberForm.settings(memberEntity);
        return memberForm;
    }

    public void updateMember(MemberForm memberForm) {
        MemberEntity member = userRepository.findById(memberForm.getUserId()).get();
        member.memberSetting(memberForm.getUserId(),memberForm.getUserName(),passwordEncoder.encode(memberForm.getPassword()));
    }
}
