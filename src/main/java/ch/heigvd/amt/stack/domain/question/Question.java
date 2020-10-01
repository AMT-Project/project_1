package ch.heigvd.amt.stack.domain.question;

import ch.heigvd.amt.stack.domain.IEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)

public class Question implements IEntity<Question, QuestionId> {
  private QuestionId id = new QuestionId();
  private String author;
  private String title;
  private String description;

  @Override
  public QuestionId getId() {
    return id;
  }

  @Override
  public Question deepClone() {
    return this.toBuilder().
            id(new QuestionId(id.asString())).
            build();
  }

  public static class QuestionBuilder{
    public Question build(){
      if(id == null){
        id = new QuestionId();
      }

      if(author == null){
        author = "nullAuth";
      }

      if(title == null){
        title = "nullTit";
      }

      if(description == null){
        description = "nullDesc";
      }
      return new Question(id, author, title, description);
    }
  }
}
