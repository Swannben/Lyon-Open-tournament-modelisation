/*==============================================================*/
/* Nom de SGBD :  MySQL 5.0                                     */
/* Date de création :  19/12/2021 04:44:49                      */
/*==============================================================*/

drop table if exists demande;

drop table if exists hotelproposeservice;

drop table if exists reservation;

drop table if exists chambre;

drop table if exists "service";

drop table if exists typechambre;

drop table if exists hotel;

drop table if exists vip;


/*==============================================================*/
/* Table : chambre                                              */
/*==============================================================*/
create table chambre
(
   idchambre            serial not null,
   idhotel              int not null,
   idtypechambre        int not null,
   numchambre           int,
   debutplagedispo      date,
   finplagedispo        date,
   primary key (idchambre)
);

/*==============================================================*/
/* Table : demande                                              */
/*==============================================================*/
create table demande
(
   iddemande            serial not null,
   idhotel              int not null,
   idtypechambre        int not null,
   demandeur            int not null,
   accompagnats         varchar(254),
   etat                 int,
   primary key (iddemande)
);

/*==============================================================*/
/* Table : hotel                                                */
/*==============================================================*/
create table hotel
(
   idhotel              serial not null,
   nom                  varchar(254),
   adresse              varchar(254),
   nbetoiles            int,
   "url"                varchar(254),
   idutilisateur        int,
   primary key (idhotel)
);

/*==============================================================*/
/* Table : hotelproposeservice                                  */
/*==============================================================*/
create table hotelproposeservice
(
   idservice            int not null,
   idhotel              int not null,
   primary key (idservice, idhotel)
);

/*==============================================================*/
/* Table : reservation                                          */
/*==============================================================*/
create table reservation
(
   idchambre            int not null,
   idvip                int not null,
   idreservation        int not null,
   datearrivee          date,
   datedepart           date,
   primary key (idchambre, idvip, idreservation)
);

/*==============================================================*/
/* Table : service                                              */
/*==============================================================*/
create table "service"
(
   idservice            serial not null,
   libelle              varchar(254),
   primary key (idservice)
);

/*==============================================================*/
/* Table : typechambre                                          */
/*==============================================================*/
create table typechambre
(
   idhotel              int not null,
   idtypechambre        serial not null,
   intitule             varchar(254),
   suite                bool,
   nblitss              int,
   nblitsd              int,
   description          varchar(254),
   url                  varchar(254),
   nbchambres           int,
   primary key (idhotel, idtypechambre)
);

/*==============================================================*/
/* Table : vip                                                  */
/*==============================================================*/
create table vip
(
   idvip                serial not null,
   accompagne           int,
   idutilisateur        int,
   nom                  varchar(254),
   prenom               varchar(254),
   numerotel            int,
   typevip              int,
   primary key (idvip)
);

alter table chambre add constraint fk_chambre_typechambre foreign key (idhotel, idtypechambre)
      references typechambre (idhotel, idtypechambre) on delete restrict on update restrict;

alter table demande add constraint fk_demande_typechambre foreign key (idhotel, idtypechambre)
      references typechambre (idhotel, idtypechambre) on delete restrict on update restrict;

alter table demande add constraint fk_demande_vip foreign key (demandeur)
      references vip (idvip) on delete restrict on update restrict;

alter table hotel add constraint fk_hotel_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete restrict on update restrict;

alter table hotelproposeservice add constraint fk_hotelproposeservice_hotel foreign key (idhotel)
      references hotel (idhotel) on delete restrict on update restrict;

alter table hotelproposeservice add constraint fk_hotelproposeservice_service foreign key (idservice)
      references service (idservice) on delete restrict on update restrict;

alter table reservation add constraint fk_reservation_chambre foreign key (idchambre)
      references chambre (idchambre) on delete restrict on update restrict;

alter table reservation add constraint fk_reservation_vip foreign key (idvip)
      references vip (idvip) on delete restrict on update restrict;

alter table typechambre add constraint fk_typechambre_hotel foreign key (idhotel)
      references hotel (idhotel) on delete restrict on update restrict;

alter table vip add constraint fk_vip_vip foreign key (accompagne)
      references vip (idvip) on delete restrict on update restrict;

alter table vip add constraint fk_vip_utilisateur foreign key (idutilisateur)
      references utilisateur (idutilisateur) on delete restrict on update restrict;