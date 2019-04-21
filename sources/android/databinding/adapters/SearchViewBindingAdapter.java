package android.databinding.adapters;

import android.annotation.TargetApi;
import android.databinding.BindingMethods;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SearchView.OnSuggestionListener;

@BindingMethods
public class SearchViewBindingAdapter {

    /* renamed from: android.databinding.adapters.SearchViewBindingAdapter$1 */
    static class C00301 implements OnQueryTextListener {
        final /* synthetic */ OnQueryTextChange val$change;
        final /* synthetic */ OnQueryTextSubmit val$submit;

        public boolean onQueryTextSubmit(String query) {
            if (this.val$submit != null) {
                return this.val$submit.onQueryTextSubmit(query);
            }
            return false;
        }

        public boolean onQueryTextChange(String newText) {
            if (this.val$change != null) {
                return this.val$change.onQueryTextChange(newText);
            }
            return false;
        }
    }

    /* renamed from: android.databinding.adapters.SearchViewBindingAdapter$2 */
    static class C00312 implements OnSuggestionListener {
        final /* synthetic */ OnSuggestionClick val$change;
        final /* synthetic */ OnSuggestionSelect val$submit;

        public boolean onSuggestionSelect(int position) {
            if (this.val$submit != null) {
                return this.val$submit.onSuggestionSelect(position);
            }
            return false;
        }

        public boolean onSuggestionClick(int position) {
            if (this.val$change != null) {
                return this.val$change.onSuggestionClick(position);
            }
            return false;
        }
    }

    @TargetApi(11)
    public interface OnQueryTextChange {
        boolean onQueryTextChange(String str);
    }

    @TargetApi(11)
    public interface OnQueryTextSubmit {
        boolean onQueryTextSubmit(String str);
    }

    @TargetApi(11)
    public interface OnSuggestionClick {
        boolean onSuggestionClick(int i);
    }

    @TargetApi(11)
    public interface OnSuggestionSelect {
        boolean onSuggestionSelect(int i);
    }
}
