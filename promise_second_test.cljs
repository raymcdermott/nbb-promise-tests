(ns promise-second-test
  (:require
    [cljs-bean.core :as cb]
    [cljs.test :as t :refer [async deftest is testing use-fixtures]]
    [promesa.core :as p]))

(use-fixtures
  :once {:before
         #(async done
            (-> (p/let [_ (p/promise 1)]
                  (js/console.log "stand up promise-second-test"))
                (p/finally done)))
         :after
         #(async done
            (-> (p/let [_ (p/promise 1)]
                  (js/console.log "tear down promise-second-test"))
                (p/finally done)))})

(def end-resolve (atom nil))
(def end-promise (p/create (fn [resolve _reject]
                             (reset! end-resolve resolve))))

(defmethod t/report [::t/default :end-test-ns]
  [m]
  (if (t/successful? m)
       (println "Second Tests passed!")
       (println "Second FAIL"))
  (@end-resolve))

(deftest p1
  (async done
    (-> (p/let [p1 (p/promise 1)]
          (is (= 1 p1)))
        (p/finally done))))


(deftest p2
  (async done
    (-> (p/let [p1 (p/promise 1)]
          (is (= 1 p1)))
        (p/finally done))))


(defn run-tests
  []
  (t/run-tests 'promise-second-test)
  end-promise)