package edu.ecm.blog;

import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQL5Dialect;

import edu.ecm.blog.domain.Author;
import edu.ecm.blog.domain.Post;

/**
 * @author damien bourdette
 * @version \$Revision$
 */
public class SchemaGenerator {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Post.class);
        configuration.addAnnotatedClass(Author.class);

        for (String string : configuration.generateSchemaCreationScript(new MySQL5Dialect())) {
            System.out.println(string + ";");
        }
    }
}
