package com.codereview.codereview.global.model.type;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Rank {
    Orion("오리온"),
    Leo("레오"),
    Virgo("자리"),
    Libra("라이브라"),
    Draco("드래곤")
    ;

    private final String rank;
}
