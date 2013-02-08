(ns tictactoe.game-setup-spec
  (:require [speclj.core :refer :all]
			[tictactoe.game-setup :refer :all]
            [tictactoe.game :refer :all]))

(describe "game_setup"

	(describe "build-new-game"
		(it "should return a board and a list with two computer players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.UltimateComputer. "x") (tictactoe.player.UltimateComputer. "x")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))
							  set-first-player (constantly [(tictactoe.player.UltimateComputer. "x") (tictactoe.player.UltimateComputer. "x")])] 
					(with-in-str "0" (build-new-game)))))
		(it "should return a board and a list of one human and one computer player"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.UltimateComputer. "x")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))
							  set-first-player (constantly [(tictactoe.player.Human. "x") (tictactoe.player.UltimateComputer. "x")])] 
					(with-in-str "1" (build-new-game)))))
		(it "should return a board and a list with two human players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))
							  set-first-player (constantly [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")])] 
					(with-in-str "2" (build-new-game))))))
	
	(describe "get-computer-difficulty"
		(with-redefs [println (constantly "")]
			(it "should set the computer player difficulty to easy" 
				(should= (tictactoe.player.EasyComputer. "o") (with-in-str "1" (get-computer-difficulty "o"))))
			(it "should set the computer player difficulty to ultimate" 
				(should= (tictactoe.player.UltimateComputer. "o") (with-in-str "2" (get-computer-difficulty "o")))))))
			
	(describe "set-first-player"
		(it "should rearrange the player list so the selected player comes first"
			(let [test-player-list [(tictactoe.player.EasyComputer. "o") (tictactoe.player.EasyComputer. "x")]]
				(with-redefs [println (constantly "")]
					(should= [(tictactoe.player.EasyComputer. "o") (tictactoe.player.EasyComputer. "x")] (with-in-str "1" (set-first-player test-player-list)))
					(should= [(tictactoe.player.EasyComputer. "x") (tictactoe.player.EasyComputer. "o")] (with-in-str "2" (set-first-player test-player-list)))))))
(run-specs)