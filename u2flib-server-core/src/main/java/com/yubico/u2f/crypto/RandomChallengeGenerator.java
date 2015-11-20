/*
 * Copyright 2014 Yubico.
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file or at
 * https://developers.google.com/open-source/licenses/bsd
 */

package com.yubico.u2f.crypto;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.security.SecureRandom;

public class RandomChallengeGenerator implements ChallengeGenerator {

    private final SecureRandom random = new SecureRandom();

    @Override
    public byte[] generateChallenge() {
        byte[] randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        byte[] hexBytes = hexToBytes("a90731aaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.arraycopy(hexBytes, 0, randomBytes, 0, 16);
//        System.out.println("------------" + randomBytes);
//        System.out.println("------------" + new String(randomBytes));
//        System.out.println("------------" + randomBytes.length);
        return randomBytes;
    }

    public static byte[] hexToBytes(String hexString) {
        HexBinaryAdapter adapter = new HexBinaryAdapter();
        byte[] bytes = adapter.unmarshal(hexString);
        return bytes;
    }

    public static void main(String[] args) {
        new RandomChallengeGenerator().generateChallenge();
    }

}
