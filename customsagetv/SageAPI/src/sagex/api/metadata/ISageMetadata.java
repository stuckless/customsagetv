package sagex.api.metadata;

/**
 * Interface to represent Sage Metadata.  This interface is just a placeholder.  If you create custom sage metadata
 * fields, then they should implement this class, sot that they can be handled by the metadata proxy classes.
 *   
 * To create a metadata instance use {@link SageMetadata}.create(MetadataInterface)
 * <pre>
 * {@link A_METADATA_INSTANCE} md = {@link SageMetadata}.create(SAGE_METADATA_INTERFACE);
 * md.setTitle("MyTitle");
 * </pre>
 * 
 * As a convenience, their is a complete metadata class called, {@link ISageMetadataALL} that contains all the
 * known metadata fields.  You can can add to this list by extending that interface, and then instanciating your
 * interface using the {@link SageMetadata}.create()
 * 
 * @see ISageMetadataALL
 * @see ISageCustomMetadataRW
 * @see ISageFormatPropertyRO
 * @see ISagePropertyRO
 * @see ISagePropertyRW
 * @see ISageRolePropertyRW
 * 
 * @author seans
 */
public interface ISageMetadata {
    public boolean isSet(String key);
}
