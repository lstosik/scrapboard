package net.purevirtual.scrapboard;

import junit.framework.TestCase;
import net.purevirtual.scrapboard.AbstractBlockMode.BlockModeContext;
import org.apache.commons.codec.DecoderException;

public class CTRTest extends TestCase {

    public CTRTest(String testName) {
        super(testName);
    }

    public void testEncodeDecode() {
        BlockModeTestUtil.testEncodeDecode(new CTR());
    }

    public void testDecode() throws DecoderException {
        System.out.println("testDecode");
        String key = "36f18357be4dbd77f050515c73fcf9f2";
        String plaintext = "435452206d6f6465206c65747320796f75206275696c6420612073747265616d206369706865722066726f6d206120626c6f636b206369706865722e";
        String ciphertext = "69dda8455c7dd4254bf353b773304eec0ec7702330098ce7f7520d1cbbb20fc3"
                + "88d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa9329";

        BlockModeContext context = BlockModeTestUtil.getAESContext(key);

        CTR instance = new CTR();
        String recoveredPlaintext = instance.decode(ciphertext, context);
        assertEquals(plaintext, recoveredPlaintext);


        plaintext = "416c776179732061766f6964207468652074776f2074696d652070616421";
        key = "36f18357be4dbd77f050515c73fcf9f2";
        ciphertext = "770b80259ec33beb2561358a9f2dc617e46218c0a53cbeca695ae45faa8952aa"
                + "0e311bde9d4e01726d3184c34451";
        context = BlockModeTestUtil.getAESContext(key);

        recoveredPlaintext = instance.decode(ciphertext, context);
        assertEquals(plaintext, recoveredPlaintext);



    }
}
