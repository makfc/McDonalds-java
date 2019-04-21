package com.google.zxing.qrcode.decoder;

import com.google.zxing.common.BitMatrix;

enum DataMask {
    DATA_MASK_000 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return ((i + j) & 1) == 0;
        }
    },
    DATA_MASK_001 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (i & 1) == 0;
        }
    },
    DATA_MASK_010 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return j % 3 == 0;
        }
    },
    DATA_MASK_011 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (i + j) % 3 == 0;
        }
    },
    DATA_MASK_100 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (((i / 2) + (j / 3)) & 1) == 0;
        }
    },
    DATA_MASK_101 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (i * j) % 6 == 0;
        }
    },
    DATA_MASK_110 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (i * j) % 6 < 3;
        }
    },
    DATA_MASK_111 {
        /* Access modifiers changed, original: 0000 */
        public boolean isMasked(int i, int j) {
            return (((i + j) + ((i * j) % 3)) & 1) == 0;
        }
    };

    public abstract boolean isMasked(int i, int i2);

    /* Access modifiers changed, original: final */
    public final void unmaskBitMatrix(BitMatrix bits, int dimension) {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (isMasked(i, j)) {
                    bits.flip(j, i);
                }
            }
        }
    }
}
