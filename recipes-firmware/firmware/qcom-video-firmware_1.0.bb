inherit qprebuilt

LICENSE          = "Qualcomm-Technologies-Inc.-Proprietary"
LIC_FILES_CHKSUM = "file://${QCOM_COMMON_LICENSE_DIR}${LICENSE};md5=58d50a3d36f27f1a1e6089308a49b403"

DESCRIPTION = "Recipe to install video firmware files on rootfs"

SRC_URI[sha256sum] = "9d67d147bda646ad87b4bd9a4f617588c501e17aa7acb7537074b4b0a2a9d7f1"

SRC_URI = "https://${PBT_ARTIFACTORY}/${PBT_BUILD_ID}/${PBT_BIN_PATH}/${BPN}_${PV}_${PBT_ARCH}.tar.gz"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILES:${PN} += "/lib/firmware"

INSANE_SKIP:${PN} = "arch"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"
