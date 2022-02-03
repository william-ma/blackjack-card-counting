package blackjack.cardcounting.models;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChallengeRepository extends MongoRepository<Challenge, String> {

    List<Challenge> findAll();

    List<Challenge> findByDifficulty(int difficulty);

    List<Challenge> findByName(String name);

}
