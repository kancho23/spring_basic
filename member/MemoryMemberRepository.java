package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}

/* 오류 처리하는 내용은 뺏음 (너무 복잡해질까봐)*/
/* hashmap은 동시성 이슈가 발생할 수 있다. 이를 해결하기 위해선?*/

