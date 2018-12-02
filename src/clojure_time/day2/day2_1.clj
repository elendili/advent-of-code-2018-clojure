(ns clojure-time.day2.day2_1
  (:require [clojure.string :as string]
            [clojure.test :refer :all]))

(let [file-content (slurp "input")
      as-string-list (string/split-lines file-content)]

  (defn get-frequencies [{:keys [twos threes]}  input_word]
    (let [freqs (frequencies input_word)
          has-twos (some #(= 2 %) (vals freqs))
          has-twos-inc (if has-twos 1 0)
          has-threes (some #(= 3 %) (vals freqs))
          has-threes-inc (if has-threes 1 0)]

      {:twos (+ twos has-twos-inc)
       :threes (+ threes has-threes-inc)}))


  (defn get-result [input_list]
    (let [answer-map (reduce get-frequencies {:twos 0 :threes 0} input_list)
          out (reduce * (vals answer-map))]
      out))

  (deftest a-test
         (testing (is (= 12
                         (get-result ["abcdef", "bababc", "abbcde", "abcccd", "aabcdd", "abcdee", "ababab",])))))
  (a-test)
  (println (str "Answer " (get-result as-string-list))))
