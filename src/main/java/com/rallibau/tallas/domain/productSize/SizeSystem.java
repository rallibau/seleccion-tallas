package com.rallibau.tallas.domain.productSize;

import java.util.HashSet;
import java.util.Set;

public class SizeSystem {

    private final String value;

    public SizeSystem(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public String valueSoreted() {
        final String[] value = {""};
        getValueCharacters().stream().sorted().forEach(character -> value[0] = value[0].concat(String.valueOf(character)));
        return value[0];
    }

    public Set<Character> getValueCharacters() {
        Set<Character> set = new HashSet<>();
        for (char c : value.toCharArray()) {
            set.add(c);
        }
        return set;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof SizeSystem)) {
            return false;
        }

        SizeSystem other = (SizeSystem) o;

        if (this.getValueCharacters().size() != other.getValueCharacters().size()) {
            return false;
        }

        return this.getValueCharacters().containsAll(other.getValueCharacters());

    }


}
