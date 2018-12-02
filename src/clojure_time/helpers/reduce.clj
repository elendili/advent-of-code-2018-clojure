(ns clojure-time.helpers.reduce)

(println (reduce (fn [acc x]
                   (if (> acc 10)
                     (reduced acc)
                     (+ acc x)))
                 0
                 (range 100)))

;; ------- get sum and drop on condition -------------------------
(defn summator [acc x]
  (if (> acc 10) (reduced acc) (+ acc x)))
(println (reduce summator (range 100)))

;; --------------------------------
(defn summator [acc x]
  (if (> acc 10) (reduced acc) (+ acc x)))

;; --------------------------------
(println "==========")
(def seq1 [1, 2, 3, -5, 4, 1, 2, 5, 6, 7, -25,])
(def seq1 [5, 2, 3, 1, -4, 2, 5, 6, 7, -25,])

(def freqs #{})
(defn duplicate-searcher [acc x]
    (do
      (println acc freqs)
      (if (contains? freqs acc)
        (reduced acc)
        (do
          (def freqs (conj freqs acc))
          (+ acc x)))))


(println (str "answer " (reduce duplicate-searcher seq1)))

(defn duplicate-searcher [{:keys [history acc]} datum]
   (if (contains? history acc)
     (reduced acc)
     {:acc (+ acc datum)
      :history (conj history acc)}))


(println (str "answer " (reduce duplicate-searcher {:history #{} :acc 0} seq1)))


