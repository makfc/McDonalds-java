package p046se.emilsjolander.stickylistheaders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* renamed from: se.emilsjolander.stickylistheaders.DistinctMultiHashMap */
class DistinctMultiHashMap<TKey, TItemValue> {
    private IDMapper<TKey, TItemValue> mIDMapper;
    LinkedHashMap<Object, List<TItemValue>> mKeyToValuesMap;
    LinkedHashMap<Object, TKey> mValueToKeyIndexer;

    /* renamed from: se.emilsjolander.stickylistheaders.DistinctMultiHashMap$IDMapper */
    interface IDMapper<TKey, TItemValue> {
        Object keyToKeyId(TKey tKey);

        Object valueToValueId(TItemValue tItemValue);
    }

    /* renamed from: se.emilsjolander.stickylistheaders.DistinctMultiHashMap$1 */
    class C46401 implements IDMapper<TKey, TItemValue> {
        C46401() {
        }

        public Object keyToKeyId(TKey key) {
            return key;
        }

        public Object valueToValueId(TItemValue value) {
            return value;
        }
    }

    DistinctMultiHashMap() {
        this(new C46401());
    }

    DistinctMultiHashMap(IDMapper<TKey, TItemValue> idMapper) {
        this.mKeyToValuesMap = new LinkedHashMap();
        this.mValueToKeyIndexer = new LinkedHashMap();
        this.mIDMapper = idMapper;
    }

    public TKey getKey(TItemValue value) {
        return this.mValueToKeyIndexer.get(this.mIDMapper.valueToValueId(value));
    }

    public void add(TKey key, TItemValue value) {
        Object keyId = this.mIDMapper.keyToKeyId(key);
        if (this.mKeyToValuesMap.get(keyId) == null) {
            this.mKeyToValuesMap.put(keyId, new ArrayList());
        }
        TKey keyForValue = getKey(value);
        if (keyForValue != null) {
            ((List) this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(keyForValue))).remove(value);
        }
        this.mValueToKeyIndexer.put(this.mIDMapper.valueToValueId(value), key);
        if (!containsValue((List) this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(key)), value)) {
            ((List) this.mKeyToValuesMap.get(this.mIDMapper.keyToKeyId(key))).add(value);
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean containsValue(List<TItemValue> list, TItemValue value) {
        for (TItemValue itemValue : list) {
            if (this.mIDMapper.valueToValueId(itemValue).equals(this.mIDMapper.valueToValueId(value))) {
                return true;
            }
        }
        return false;
    }
}
