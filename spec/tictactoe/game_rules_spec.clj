(ns tictactoe.io-spec
  (:require [speclj.core :refer :all]
			[tictactoe.board :refer :all]
            [tictactoe.game_rules :refer :all]))

(describe "game_rules"
	(context "game-over?"
		(let [full-board (make-board 9 "x") empty-board (make-board 9 nil) row-vector (generate-rows)]
			(it "should return false if the game isn't over" (should= false (game-over? empty-board)))
			(it "should return false if the game isn't over" (should= true (game-over? full-board)))))

	(context "valid-move?"
		(let [board (make-board 9 nil)]
			(it "should return false on invalid moves" (should= false (valid-move? board "asdf")))
			(it "should return false on invalid moves" (should= false (valid-move? board "12222")))
			(it "should return true on valid moves" (should= true (valid-move? board "1")))))
)

(run-specs)