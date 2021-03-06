package talex.zsw.basecore.view.imageview.scaleimage.decoder;

import androidx.annotation.NonNull;

/**
 * @author vondear
 * @param <T> The base type of the decoder this factory will produce.
 *
 * Compatibility factory to instantiate decoders with empty public constructors.
 */
public class CompatDecoderFactory<T> implements DecoderFactory<T> {
    private Class<? extends T> clazz;

    public CompatDecoderFactory(@NonNull Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T make() throws IllegalAccessException, InstantiationException {
        return clazz.newInstance();
    }
}
