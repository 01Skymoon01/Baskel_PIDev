<?php

namespace LivraisonBundle\Repository;

/**
 * livraisonRepository
 *
 * This class was generated by the Doctrine ORM. Add your own custom
 * repository methods below.
 */
class livraisonRepository extends \Doctrine\ORM\EntityRepository
{

    public function NBRLivraison($idl) {
        $query = $this->getEntityManager()->createQuery("SELECT COUNT(liv) FROM LivraisonBundle:Livraison liv WHERE liv.idLivreur=:idl AND liv.dateLivraison IS NOT NULL")
            ->setParameter('idl',$idl);

        $s= $query->getSingleScalarResult();
        return $s;
    }

    public function getCommande($id){

        $query = $this->getEntityManager()
            ->createQuery(" select c.id , c.dateLivraison , c.codeConf  from LivraisonBundle:livraison c  
 where c.idCommande =:id ")
            ->setParameter('id', $id)
        ;
        return $query->getResult();
    }
}