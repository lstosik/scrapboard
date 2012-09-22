package net.purevirtual.scrapboard;

import junit.framework.TestCase;
import net.purevirtual.scrapboard.AbstractBlockMode.BlockModeContext;
import org.apache.commons.codec.DecoderException;

public class CBCTest extends TestCase {

    public CBCTest(String testName) {
        super(testName);
    }

    public void testEncodeDecode() {
        BlockModeTestUtil.testEncodeDecode(new CBC());
    }

    public void testDecode() throws DecoderException {
        System.out.println("testDecode");
        CBC cbc = new CBC();

        String plaintext = "426173696320434243206d6f646520656e6372797074696f6e206e656564732070616464696e672e";
        String key = "140b41b22a29beb4061bda66b6747e14";
        String ciphertext = "4ca00ff4c898d61e1edbf1800618fb2828a226d160dad07883d04e008a7897ee"
                + "2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81";

        BlockModeContext context = BlockModeTestUtil.getAESContext(key);

        String recoveredPlaintext = cbc.decode(ciphertext, context);
        assertEquals(plaintext, recoveredPlaintext);

        plaintext = "4f757220696d706c656d656e746174696f6e20757365732072616e642e204956";
        key = "140b41b22a29beb4061bda66b6747e14";
        ciphertext = "5b68629feb8606f9a6667670b75b38a5b4832d0f26e1ab7da33249de7d4afc48"
                + "e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253";

        context = BlockModeTestUtil.getAESContext(key);
        recoveredPlaintext = cbc.decode(ciphertext, context);
        assertEquals(plaintext, recoveredPlaintext);

    }
}
