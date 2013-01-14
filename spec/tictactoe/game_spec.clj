(ns tictactoe.game-spec
  (:require [speclj.core :refer :all]
            [tictactoe.game :refer :all]
			[tictactoe.player :refer :all]
			[tictactoe.board :refer :all]))
			
(describe "Game"
	(def test-player-list [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")])
	
	; (context "start"
	; 	(let [board [nil "x" "x" nil nil nil nil nil nil nil]
	; 		  player-list test-player-list]
	; 		(it "should end a game loop and return a string if the game is over" 
	; 			(should= "game over" (with-in-str "1" (start board player-list))))))
	
	; (context "set-up-new-game"
	; 	(let [board [nil "x" "x" nil nil nil nil nil nil nil]
	; 		  player-list test-player-list]
	; 		(it "should set up and start a new game" 
	; 			(should= "some way to test a recursive function" (set-up-new-game)))))
	
	(context "alternate-players"
		(let [board (make-board 9 nil) 
			  player-list test-player-list]
			(it "should return the other player instance"
				(should= (nth player-list 0) (alternate-players player-list "x"))))))

(run-specs)