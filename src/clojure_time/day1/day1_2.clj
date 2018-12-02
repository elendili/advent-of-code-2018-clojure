(ns clojure-time.day1.day1_2
  (:require [clojure.string :as string]
            [clojure.test :refer :all]))


(let [file-content (slurp "input")
      as-string-list (string/split-lines file-content)
      as-number-list (map read-string as-string-list)]


  (defn duplicate-searcher [{:keys [history acc]} datum]
    (let [new-acc (+ acc datum)]
      (if (contains? history new-acc)
        (reduced {:acc new-acc :history history :found true})
        {:acc new-acc
         :history (conj history acc)
         :found false})))

  (defn get-result [input_list, size]
    (let [ result (reduce duplicate-searcher {:history #{} :acc 0}
                          (take size (flatten (repeat input_list))))]
      (if (get result :found) (get result :acc) "Not found!")))


  (deftest a-test
           (testing (is (= 7 (get-result [5, 2, 3, 1, -4, 2, 5, 6, 7, -25,], 100))))
           (testing (is (= 0 (get-result [1,-1], 2))))
           (testing (is (= 10 (get-result [+3, +3, +4, -2, -4], 30))))
           (testing (is (= 5 (get-result [-6, +3, +8, +5, -6], 30))))
           (testing (is (= 14 (get-result [+7, +7, -2, -7, -4], 30)))))

  (a-test)

  (println (str "Big answer " (get-result as-number-list, 10000000))))



