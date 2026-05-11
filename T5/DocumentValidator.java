
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocumentValidator {

    // FIX: Use SLF4J logger instead of printStackTrace()
    private static final Logger logger = LoggerFactory.getLogger(DocumentValidator.class);

    public ValidationResult validate(Document doc) {
        try {

            // FIX: 1
            if (doc == null) {
                throw new IllegalArgumentException("Document is null");
            }

            String content = doc.extractContent();

            // FIX: 2
            if (content == null || content.trim().isEmpty()) {
                throw new IllegalArgumentException("Empty content");
            }

            return runValidationRules(content);

        } catch (IllegalArgumentException e) {

            // FIX: 3 for logs
            logger.warn("Validation failed: {}", e.getMessage());

            // FIX: Return failed ValidationResult instead of null
            return ValidationResult.failed(e.getMessage());

        } catch (Exception e) {

            // FIX: error log
            logger.error("Unexpected error while validating document", e);

            // FIX: throw exception
            throw new RuntimeException("Document validation failed", e);
        }
    }

    public void validateBatch(List<Document> docs) {

        if (docs == null || docs.isEmpty()) {
            logger.warn("Document list is empty");
            return;
        }

        for (Document doc : docs) {
            try {

                ValidationResult r = validate(doc);

                // FIX: Avoid NPE
                if (r != null && r.isValid()) {
                    saveResult(r);
                }

            } catch (Exception e) {

                
                logger.error("Failed to process document in batch", e);
            }
        }
    }


    private ValidationResult runValidationRules(String content) {
        return null;
    }

    private void saveResult(ValidationResult r) {
    }
}