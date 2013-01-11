(defproject tictactoe "0.1.0-SNAPSHOT"
  :description "Tic Tac Toe in Clojure"
  :main tictactoe.runner
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[speclj "2.5.0"]
			    [org.clojure/clojure "1.4.0"]]
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec/"])
