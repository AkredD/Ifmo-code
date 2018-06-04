data BinTree e = Empty | Node e (BinTree e) (BinTree e) deriving(Show, Eq, Ord)
--In-order
--foldMap (:[]) (Node 1 (Node 2 (Node 3 Empty Empty) (Node 4 Empty Empty)) (Node 5 Empty Empty))
--3-2-4-1-5
instance Foldable BinTree where
    foldMap f Empty = mempty
    foldMap f (Node e Empty Empty) = f e
    foldMap f (Node e l r) = (foldMap f l) `mappend` (f e) `mappend` (foldMap f r)
