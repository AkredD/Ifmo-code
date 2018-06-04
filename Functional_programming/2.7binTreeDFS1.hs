data BinTree e = Empty | Node e (BinTree e) (BinTree e) deriving(Show, Eq, Ord)
--Pre-order
--foldMap (:[]) (Node 1 (Node 2 (Node 3 Empty Empty) (Node 4 Empty Empty)) (Node 5 Empty Empty))
-- 1-2-3-4-5
instance Foldable BinTree where
    foldMap f Empty = mempty
    foldMap f (Node e Empty Empty) = f e
    foldMap f (Node e l r) = (f e) `mappend` (foldMap f l) `mappend` (foldMap f r)

