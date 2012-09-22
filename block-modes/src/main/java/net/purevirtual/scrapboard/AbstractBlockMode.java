package net.purevirtual.scrapboard;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.BlockCipher;

public abstract class AbstractBlockMode {

    public abstract byte[] encode(final byte iv[], final byte plaintext[], final BlockModeContext context);

    public abstract byte[] decode(final byte cipherText[], final BlockModeContext context);

    public final String encode(final String hexIv, final String hexPlaintext, final BlockModeContext context) throws DecoderException {
        byte iv[] = Hex.decodeHex(hexIv.toCharArray());
        byte plaintext[] = Hex.decodeHex(hexPlaintext.toCharArray());
        byte ciphertext[] = encode(iv, plaintext, context);
        return Hex.encodeHexString(ciphertext);
    }

    public final String decode(final String hexCipherText, final BlockModeContext context) throws DecoderException {
        byte cipherText[] = Hex.decodeHex(hexCipherText.toCharArray());
        byte plaintext[] = decode(cipherText, context);
        return Hex.encodeHexString(plaintext);
    }

    public static class BlockModeContext {

        private final BlockCipher encodingCipher;
        private final BlockCipher decodingCipher;

        public BlockModeContext(final BlockCipher encodingCipher, final BlockCipher decodingCipher) {
            this.encodingCipher = encodingCipher;
            this.decodingCipher = decodingCipher;
        }

        public BlockCipher getDecodingCipher() {
            return decodingCipher;
        }

        public BlockCipher getEncodingCipher() {
            return encodingCipher;
        }
    }
}
